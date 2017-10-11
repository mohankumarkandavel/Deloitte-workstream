import {Component, OnInit} from '@angular/core';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {Task} from './task.model';
import {Subscription} from 'rxjs';
import {TaskService} from '../services/task.service';
import {RankService} from '../services/rank.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './manager.component.html',
  styleUrls: ['./tasks.component.css']
})

export class ManagerComponent implements OnInit {

  private model: Task = new Task();
  private droppedTaskGroup: string;
  private droppedTaskLimit: number;

  private selectedEmployeeArray: Task[] = [];

  loading: Subscription;

  private availabilityRangeError: boolean = false;
  private peopleRangeError: boolean = false;
  private resourceRangeError: boolean = false;

  private addTaskModal: NgbModalRef;

  constructor(private modalService: NgbModal, private taskService: TaskService, private rankService: RankService) {
  }

  ngOnInit() {
    this.getAllTasks();
  }

  getAllTasks() {
    let userId = localStorage.getItem("userId");
    this.taskService.getUsersTasks(userId);
  }

  onNewTask(id: string) {
    this.addTaskModal = this.modalService.open(id, {windowClass: 'task-modal', size: 'lg'});
  }

  addTask() {
    this.resourceRangeError = Number(this.model.attribute.resource) < 1 || Number(this.model.attribute.resource) > 6;
    this.availabilityRangeError = Number(this.model.attribute.availability) < 1 || Number(this.model.attribute.resource) > 6;
    this.peopleRangeError = Number(this.model.numAssigneesRequred) < 1;

    if (!this.resourceRangeError && !this.peopleRangeError && !this.availabilityRangeError) {
      this.model.status = "Draft";
      this.model.owner = localStorage.getItem("userId");
      this.taskService.addTask(this.model);
      this.addTaskModal.close();
    }
  }

  onTaskDrop(e: any, id: string) {
    this.droppedTaskLimit = +e.dragData.numAssigneesRequired;
    this.modalService.open(id, {windowClass: 'recommend-modal', size: 'lg'}).result.then(
      (result) => {
        if (result === 'Cancel') {
          this.emptySelectedEmployeeArray();
        } else if (result === 'Send') {
          this.updateTaskStatus(e.dragData);
          this.taskService.sendInvite(e.dragData, this.selectedEmployeeArray);
          this.emptySelectedEmployeeArray();
          this.getAllTasks();
        }
      },
      (any) => this.emptySelectedEmployeeArray()
    ).catch(
      (reason) => this.emptySelectedEmployeeArray()
    );

    this.loading = this.rankService.getBestTeamMembers(e.dragData);
    this.droppedTaskGroup = e.dragData.group;
  }

  updateTaskStatus(task: Task) {
    this.taskService.updateTaskStatus(task, "Pending", "");
  }

  emptySelectedEmployeeArray() {
    this.selectedEmployeeArray.splice(0, this.selectedEmployeeArray.length); // clear array here
  }

  addThisEmployeeToArray(employee: any, event) {
    this.toggleEmployee(employee);
  }

  toggleEmployee(employee) {
    const index = this.selectedEmployeeArray.indexOf(employee);
    if (index === -1) {
      if (this.selectedEmployeeArray.length < this.droppedTaskLimit) {
        this.selectedEmployeeArray.push(employee);
      }
    } else {
      this.selectedEmployeeArray.splice(index, 1);
    }
    // index === -1 ? this.selectedEmployeeArray.push(employee) : this.selectedEmployeeArray.splice(index, 1);
  }

  newTask(id: string) {
    this.modalService.open(id);
  }

  isEmployeeSelected(employee: any) {
    return this.selectedEmployeeArray.indexOf(employee) !== -1;
  }
}

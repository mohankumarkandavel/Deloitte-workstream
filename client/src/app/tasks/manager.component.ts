import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Http, Headers, RequestOptions} from '@angular/http';
import {Task} from "./task.model";
import {Subscription, Observable} from 'rxjs';
import {TaskService} from "../services/task.service";
import {RankService} from "../services/rank.service";

@Component({
  selector: 'app-tasks',
  templateUrl: './manager.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./tasks.component.css']
})

export class ManagerComponent implements OnInit {

  private model: Task = new Task();
  private tasks: any[];

  private droppedTaskGroup: string;

  selectedEmployeeArray = [];

  loading: Subscription;

  constructor(private modalService: NgbModal, private http: Http, private taskService: TaskService, private rankService: RankService) {
  }

  ngOnInit() {
    this.getAllTasks();
  }

  getAllTasks() {
    let userId = localStorage.getItem("userId");
    this.taskService.getUsersTasks(userId);
  }

  onNewTask(id: string) {
    this.modalService.open(id, {windowClass: 'task-modal'});
    console.log('found');
  }

  addTask() {
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    this.model.status = "Draft";
    this.model.owner = localStorage.getItem("userId");
    this.taskService.addTask(options, this.model);
  }

  onTaskDrop(e: any, id: string) {
    this.modalService.open(id, {windowClass: 'recommend-modal'}).result.then((result) => {
      if (result === 'Cancel') {
        this.emptySelectedEmployeeArray();
      } else if (result === 'Send') {
        this.updateTaskStatus(e.dragData);
        // this.modalService.open('invitationSend', { windowClass: 'alert-modal' });
        // todo send invitation here
        this.emptySelectedEmployeeArray();
        this.getAllTasks();
      }
      this.rankService.teamMembers.length = 0;
    }, any => {
      this.emptySelectedEmployeeArray();
    });

    this.loading = this.rankService.getBestTeamMembers(e.dragData);
    this.droppedTaskGroup = e.dragData.group;
  }

  updateTaskStatus(task: Task) {
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    this.taskService.updateTaskStatus(options, task);
  }

  emptySelectedEmployeeArray() {
    this.selectedEmployeeArray.splice(0, this.selectedEmployeeArray.length); // clear array here
  }

  toggleItemInArr(arr, item) {
    const index = arr.indexOf(item);
    index === -1 ? arr.push(item) : arr.splice(index, 1);
  }

  addThisEmployeeToArray(employee: any, event) {
    if (!event.ctrlKey) {
      this.selectedEmployeeArray = [];
    }
    this.toggleItemInArr(this.selectedEmployeeArray, employee);
  }

  newTask(id: string) {
    this.modalService.open(id);
    console.log('found');
  }

  isEmployeeSelected(employee: any) {
    return this.selectedEmployeeArray.indexOf(employee) !== -1;
  }
}

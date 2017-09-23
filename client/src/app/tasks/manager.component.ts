import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {Http, Headers, RequestOptions} from '@angular/http';
import {Task} from "./task.model";
import {Subscription, Observable} from 'rxjs';

@Component({
  selector: 'app-tasks',
  templateUrl: './manager.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./tasks.component.css']
})

export class ManagerComponent implements OnInit {

  private model = new Task("", "", "", "", "", "", {experience:"", interest:"", availability:"", resource:""}, "");
  private tasks: any[];

  private draftTasks: any[];
  private assignedTasks: any[];
  private pendingTasks: any[];

  private droppedTaskGroup: string;
  private teamMembers: any[] = [];

  pendingTaskList = [];
  selectedEmployeeArray = [];

  loading: Subscription;

  constructor(private modalService: NgbModal, private router: Router, private http: Http) {
  }

  onNewTask(id: string) {
    this.modalService.open(id, {windowClass: 'task-modal'});
    console.log('found');
  }

  addTask() {
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    this.model.status = "Draft";

    this.http.post("http://localhost:8080/task", JSON.stringify(this.model), options)
      .subscribe(
        data => {
          this.getAllTasks();
        },
        err => console.error(err),
        () => {
          console.log("complete")
        });

  }

  onTaskDrop(e: any, id: string) {

    this.modalService.open(id, {windowClass: 'recommend-modal'}).result.then((result) => {
      if (result === 'Cancel') {
        this.onRemoveTask(e.dragData, this.pendingTaskList);
        this.emptySelectedEmployeeArray();
      } else if (result === 'Send') {
        this.onRemoveTask(e.dragData, this.tasks);
        this.updateTaskStatus(e.dragData);
        // this.modalService.open('invitationSend', { windowClass: 'alert-modal' });
        // todo send invitation here
        this.emptySelectedEmployeeArray();
        this.getAllTasks();
      }
      this.teamMembers.length = 0;
    }, any => {
      this.emptySelectedEmployeeArray();
      this.onRemoveTask(e.dragData, this.pendingTaskList);
    });

    // FIXME: Extract this into its own serivce
    this.loading = this.http.get("http://localhost:8080/rank/" + e.dragData.id)
    .subscribe(
      (response) => {
        if (response.ok) {
          this.loadTeamMembers(JSON.parse(response.text()));

          this.droppedTaskGroup = e.dragData.group;
        }
      },
      (error) => console.log(`Error:${error.toString()}`),
      () => console.log("Complete")
    );
  }

  loadTeamMembers(teamMembers: any[]) {
    Observable.zip(
      Observable.from(teamMembers),
      Observable.interval(100)
    ).subscribe(
      res => {
        this.teamMembers.push(res[0]);
      },
      err => console.log(err.toString())
    )
  }

  updateTaskStatus(task: Task) {
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});

    this.http.put("http://localhost:8080/task" , JSON.stringify(task), options)
    .subscribe(
      (response) => {
        if (response.ok) {
          this.getAllTasks();
        }
      },
      (error) => console.log(error.toString())
    );
  }

  onRemoveTask(task: any, list: Array<any>) {
    const index = list.map(function (e) {
      return e.taskName;
    }).indexOf(task.taskName);
    list.splice(index, 1);
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

  ngOnInit() {
    this.getAllTasks();
  }

  getAllTasks() {
    this.http.get('http://localhost:8080/task').subscribe(
      (response) => {
        if (response.ok) {
          this.tasks = JSON.parse(response.text());
          this.draftTasks = this.tasks.filter(task => task.status === "Draft");
          this.pendingTasks = this.tasks.filter(task => task.status === "Pending");
          this.assignedTasks = this.tasks.filter(task => task.status === "Assigned");
        }
      }
    );
  }

  newTask(id: string) {
    this.modalService.open(id);
    console.log('found');
  }

  isEmployeeSelected(employee: any) {
    return this.selectedEmployeeArray.indexOf(employee) !== -1;
  }
}

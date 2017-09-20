import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {Http, Headers, RequestOptions} from '@angular/http';
import {Task} from "./task.model";

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./tasks.component.css']
})

export class TasksComponent implements OnInit {

  private model = new Task("", "", "", "", "", {experience:"", interest:"", availability:"", resource:""}, "");
  private tasks: any[];

  private draftTasks: any[];
  private pendingTasks: any[];
  private assignedTasks: any[];

  private droppedTaskGroup: string;
  private teamMembers: any[];

  pendingTaskList = [];
  selectedEmployeeArray = [];

  constructor(private modalService: NgbModal, private router: Router, private http: Http) {
  }

  onNewTask(id: string) {
    this.modalService.open(id, {windowClass: 'task-modal'});
    console.log('found');
  }

  addTask() {
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});

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

    // FIXME: Extract this into its own serivce
    this.http.get("http://localhost:8080/rank/" + e.dragData.id).subscribe((response) => {
        if (response.ok) {
          this.teamMembers = JSON.parse(response.text());
          this.droppedTaskGroup = e.dragData.group;
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
            }
          }, any => {
            this.emptySelectedEmployeeArray();
            this.onRemoveTask(e.dragData, this.pendingTaskList);
          });
          this.getAllTasks();
        }
      },
      (error) => console.log(`Error:${error.toString()}`),
      () => console.log("Complete")
    );
  }

  updateTaskStatus(task: Task) {

    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});

    this.http.put("http://localhost:8080/task" , JSON.stringify(task), options).subscribe((response) => {
      if (response.ok) {
        this.getAllTasks();
      }
    })
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
          this.draftTasks = this.tasks.filter(task => task.status === "DRAFT")
          this.pendingTasks = this.tasks.filter(task => task.status === "PENDING")
          this.assignedTasks = this.tasks.filter(task => task.status === "ASSIGNED")
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

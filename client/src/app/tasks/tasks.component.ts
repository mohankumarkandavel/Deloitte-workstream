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

  private model = new Task("","","","","","");
  private tasks: any[];
  pendingTaskList = [];
  selectedEmployeeArray = [];

  constructor(private modalService: NgbModal, private router: Router, private http: Http) {
  }

  onNewTask(id: string) {
    this.modalService.open(id, {windowClass: 'task-modal'});
    console.log('found');
  }

  addTask() {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    this.http.post("http://localhost:8080/task", JSON.stringify(this.model), options)/*.map((res) => res.json())*/.subscribe(
      data => { console.log(data) },
      err => console.error(err),
      () => { console.log("complete") });

  }

  onTaskDrop(e: any, id: string) {
    // Get the dropped data here
    this.pendingTaskList.push(e.dragData);
    // todo change task status
    this.modalService.open(id, {windowClass: 'recommend-modal'}).result.then((result) => {
      if (result === 'Cancel click') {
        this.onRemoveTask(e.dragData, this.pendingTaskList);
        this.emptySelectedEmployeeArray();
      } else if (result === 'invitationSend') {
        this.onRemoveTask(e.dragData, this.tasks);
        // this.modalService.open('invitationSend', { windowClass: 'alert-modal' });
        // todo send invitation here
        this.emptySelectedEmployeeArray();
      }
    }, any => {
      this.emptySelectedEmployeeArray();
      this.onRemoveTask(e.dragData, this.pendingTaskList);
    });
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
    this.http.get('http://localhost:8080/task').subscribe(
      (response) => {
        if (response.ok) {
          this.tasks = JSON.parse(response.text());
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

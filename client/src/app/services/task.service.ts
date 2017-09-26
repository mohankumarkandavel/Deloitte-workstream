import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import {Task} from "../tasks/task.model";

@Injectable()
export class TaskService {

  private taskURL: string = 'http://localhost:8080/task';

  private tasks: any[];
  draftTasks: any[];
  assignedTasks: any[];
  pendingTasks: any[] = [];

  constructor(private http: Http) {
  }

  getUsersTasks(userId: string) {
    this.http.get(this.taskURL + '/' + userId).subscribe(
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

  addTask(task: Task) {
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    this.http.post(this.taskURL, JSON.stringify(task), options)
      .subscribe(
        data => {
          this.getUsersTasks(localStorage.getItem("userId"));
        },
        err => console.error(err),
        () => {
          console.log("complete")
        });
  }

  updateTaskStatus(task: Task, status: string) {
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    task.status = status;
    this.http.put(this.taskURL, JSON.stringify(task), options)
      .subscribe(
        (response) => {
          if (response.ok) {
            this.getUsersTasks(localStorage.getItem("userId"));
          }
        },
        (error) => console.log(error.toString())
      )
  }

  sendInvite(task:Task, selectedTeamMembersId: Task[]) {
    this.http.put(this.taskURL + '/' + task.id, selectedTeamMembersId[0].id).subscribe((response) => {
      if (response.ok) {
        console.log("Sent")
      }
    },
      (error) => console.log(error.toString())
    )
  }
}

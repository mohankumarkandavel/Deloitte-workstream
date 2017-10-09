import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import {Task} from "../tasks/task.model";

@Injectable()
export class TaskService {

  private taskURL: string = 'http://localhost:8080/task';

  private tasks: any[] = [];
  draftTasks: any[] = [];
  assignedTasks: any[] = [];
  pendingTasks: any[] = [];

  constructor(private http: Http) {
  }

  clearTasks() : void {
    this.tasks.length = 0;
    this.draftTasks.length = 0;
    this.assignedTasks.length = 0;
    this.pendingTasks.length = 0;
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

  updateTaskStatus(task: Task, status: string, reasonForDeclining: string) {
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    task.status = status;
    task.reasonForDeclining = reasonForDeclining;

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

  sendInvite(task: Task, selectedTeamMembers: any[]) {
    let teamMemberIds = selectedTeamMembers.map(teamMember => teamMember.id);
    this.http.put(`${this.taskURL}/${task.id}/request-assignee`, teamMemberIds).subscribe(
      (response) => {
        if (response.ok) {
          console.log("Invite was sent");
        }
      },
      (error) => console.log(error.toString())
    )
  }

  acceptPendingTask(task: Task) {
    // set the tasks status to Assigned and update the task's assignees
    this.updateTaskStatus(task, "Assigned", "");
    this.http.put(this.taskURL + '/add-assignee/' + task.id, localStorage.getItem("userId")).subscribe((response) => {
      if (response.ok) {
        console.log("Task was accepted")
      }
    },
      (error => console.log(error.toString)))
  }

  requestMoreInformation(task: Task) {
    this.http.put(`${this.taskURL}/${task.id}/requestsById`, localStorage.getItem("userId")).subscribe(
      (response) => {
        if (response.ok) {
          console.log("ok")
        } else if (response.status === 303) {
          console.log("303 " + response.headers.get("location"));
        }
      },
      (error) => console.error(error.toString())
    )
  }
}

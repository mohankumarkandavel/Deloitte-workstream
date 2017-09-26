import {Injectable} from "@angular/core";
import {Http, RequestOptions} from "@angular/http";
import {Task} from "../tasks/task.model";

@Injectable()
export class TaskService {

  private taskURL: string = 'http://localhost:8080/task';

  private tasks: any[];
  draftTasks: any[];
  assignedTasks: any[];
  pendingTasks: any[];

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

  addTask(options: RequestOptions, task: Task) {
    this.http.post(this.taskURL, JSON.stringify(task), options)
      .subscribe(
        data => {
          this.getUsersTasks(task.owner);
        },
        err => console.error(err),
        () => {
          console.log("complete")
        });
  }

  updateTaskStatus(options: RequestOptions, task: Task) {
    this.http.put(this.taskURL, JSON.stringify(task), options)
      .subscribe(
        (response) => {
          if (response.ok) {
            this.getUsersTasks(task.owner);
          }
        },
        (error) => console.log(error.toString())
      )
  }
}

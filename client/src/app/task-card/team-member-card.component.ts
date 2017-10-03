import {Component, Input} from '@angular/core';
import {TaskService} from "../services/task.service";
import {Task} from "../tasks/task.model";

@Component({
  selector: 'app-team-member-card',
  templateUrl: './team-member-card.component.html',
  styleUrls: ['./team-member-card.component.css'],
})

export class TeamMemberCardComponent {
  @Input()
  task: Task;
  shown: string = 'NONE';
  showRequestMoreInfo: boolean = false;

  constructor(private taskService:TaskService) {
  }

  onTaskAccept() {
    this.taskService.acceptPendingTask(this.task);
  }

  onRequestMoreInformation() {
    this.taskService.updateTaskStatus(this.task, "Pending", this.task.reasonForDeclining, true);
  }

  onTaskDecline(){
    this.taskService.updateTaskStatus(this.task, "Draft", this.task.reasonForDeclining, false);
  }
}

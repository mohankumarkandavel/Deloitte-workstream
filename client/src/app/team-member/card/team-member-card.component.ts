import {Component, Input} from '@angular/core';
import {TaskService} from '../../services/task.service';
import {Task} from '../../models/task.model';

@Component({
  selector: 'app-team-member-card',
  templateUrl: './team-member-card.component.html',
  styleUrls: ['./team-member-card.component.css'],
})

export class TeamMemberCardComponent {
  @Input()
  task: Task;
  shown = 'NONE';
  showRequestMoreInfo = false;

  constructor(private taskService: TaskService) {
  }

  onTaskAccept() {
    this.taskService.acceptPendingTask(this.task);
  }

  onTaskDone() {
    this.taskService.updateTaskStatus(this.task, 'Done', '');
  }

  onRequestMoreInformation() {
    this.taskService.requestMoreInformation(this.task);
  }

  onTaskDecline() {
    this.taskService.updateTaskStatus(this.task, 'Draft', this.task.reasonForDeclining);
  }

  private hasUserRequestedInfo(): boolean {
    const userId: number = parseInt(localStorage.getItem('userId'), 10);
    const hasRequested: boolean = this.task.requestsById.indexOf(userId) !== -1;
    return hasRequested;
  }
}

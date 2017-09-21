import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-team-member',
  templateUrl: './team-member.component.html',
  styleUrls: ['./tasks.component.css']
})

export class TeamMemberComponent implements OnInit {

  private shown: string = 'NONE';

  private pendingTasks: any[];
  private assignedTasks: any[];

  ngOnInit(): void {
  }

}

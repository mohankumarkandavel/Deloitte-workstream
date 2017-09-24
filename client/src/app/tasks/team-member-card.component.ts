import {Component} from '@angular/core';

@Component({
  selector: 'app-team-member-card',
  templateUrl: './team-member-card.component.html',
  styleUrls: ['./team-member-card.component.css'],
  inputs: ['task']
})

export class TeamMemberCardComponent {
  shown: string = 'NONE';
}

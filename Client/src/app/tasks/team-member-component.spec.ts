import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamMemberComponent } from './team-member.component';
import { TeamMemberCardComponent} from '../task-card/team-member-card.component';
import { MdCardModule, MdTooltipModule} from '@angular/material';
import { FormsModule} from '@angular/forms';
import {TaskService} from '../services/task.service';

describe('TeamMemberComponent', () => {
  let component: TeamMemberComponent;
  let fixture: ComponentFixture<TeamMemberComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ MdCardModule, MdTooltipModule, FormsModule ],
      declarations: [ TeamMemberComponent, TeamMemberCardComponent ],
      providers: [ { provide: TaskService}]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamMemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

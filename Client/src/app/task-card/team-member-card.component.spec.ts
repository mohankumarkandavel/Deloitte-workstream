import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamMemberCardComponent } from './team-member-card.component';
import { MdCardModule, MdTooltipModule } from '@angular/material';
import { FormsModule} from '@angular/forms';
import {TaskService} from '../services/task.service';

describe('TeamMemberCardComponent', () => {
  let component: TeamMemberCardComponent;
  let fixture: ComponentFixture<TeamMemberCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ MdCardModule, MdTooltipModule, FormsModule ],
      declarations: [ TeamMemberCardComponent ],
      providers: [ { provide: TaskService}]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamMemberCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

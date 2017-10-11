import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerCardComponent } from './manager-card.component';
import { MdCardModule, MdTooltipModule } from '@angular/material';
import {TaskService} from '../services/task.service';

describe('ManagerCardComponent', () => {
  let component: ManagerCardComponent;
  let fixture: ComponentFixture<ManagerCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ MdCardModule, MdTooltipModule ],
      declarations: [ ManagerCardComponent ],
      providers: [ { provide: TaskService, useClass: TaskService}]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

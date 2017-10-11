import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ManagerCardComponent } from '../task-card/manager-card.component';
import { Ng2DragDropModule} from 'ng2-drag-drop';
import { FormsModule} from '@angular/forms';
import {BusyModule} from 'angular2-busy';
import { MdTooltipModule} from '@angular/material';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { ManagerComponent } from './manager.component';
import {NgbModalStack} from '@ng-bootstrap/ng-bootstrap/modal/modal-stack';
import {TaskService} from '../services/task.service';
import {RankService} from '../services/rank.service';
import {Ng2DragDropService} from 'ng2-drag-drop/src/services/ng2-drag-drop.service';

describe('ManagerComponent', () => {
  let component: ManagerComponent;
  let fixture: ComponentFixture<ManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ Ng2DragDropModule, FormsModule, BusyModule, MdTooltipModule, NgbModule ],
      declarations: [ ManagerComponent, ManagerCardComponent ],
      providers: [{ provide: NgbModalStack }, { provide: TaskService }, { provide: RankService }, { provide: Ng2DragDropService }]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

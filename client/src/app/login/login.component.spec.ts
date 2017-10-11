import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import {Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';
import {TaskService} from '../services/task.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ FormsModule, RouterTestingModule.withRoutes([])],
      declarations: [ LoginComponent ],
      providers: [ { provide: Router, useClass: class { navigate = jasmine.createSpy('navigate'); } },
        { provide: AuthenticationService, useClass: AuthenticationService }, { provide: TaskService, useClass: TaskService}]

    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

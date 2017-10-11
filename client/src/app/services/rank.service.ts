import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Subscription, Observable} from 'rxjs';
import {Task} from '../models/task.model';

@Injectable()
export class RankService {

  teamMembers: any[] = [];

  private rankURL = 'http://localhost:8080/rank/';

  constructor(private http: Http) {
  }

  getBestTeamMembers(task: Task): Subscription {
    this.teamMembers.length = 0;
    return this.http.get(this.rankURL + task.id)
      .subscribe(
        (response) => {
          if (response.ok) {
            this.loadTeamMembers(JSON.parse(response.text()));
          }
        },
        (error) => console.log(`Error:${error.toString()}`),
        () => console.log('Complete')
      );
  }

  private loadTeamMembers(teamMembers: any[]) {
    Observable.zip(
      Observable.from(teamMembers),
      Observable.interval(100)
    ).subscribe(
      res => {
        this.teamMembers.push(res[0]);
      },
      err => console.log(err.toString())
    );
  }
}

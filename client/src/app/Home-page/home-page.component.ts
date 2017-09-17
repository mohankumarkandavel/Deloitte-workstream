import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {

  draftTaskList: any[];
  pendingTaskList=[];
  selectedEmployeeArray = [];

  //add dummy employee list here
  employees = [
    {
      ranking: 1,
      icon:'',
      name: 'name1',
      availability: '3',
      experience:'4',
      interest:'5'
    },
    {
      ranking: 2,
      icon:'',
      name: 'name1',
      availability: '3',
      experience:'4',
      interest:'5'
    },
    {
      ranking: 3,
      icon:'',
      name: 'name1',
      availability: '3',
      experience:'4',
      interest:'5'
    },
    {
      ranking: 4,
      icon:'',
      name: 'name1',
      availability: '3',
      experience:'4',
      interest:'5'
    },
    {
      ranking: 5,
      icon:'',
      name: 'name1',
      availability: '3',
      experience:'4',
      interest:'5'
    }];

  //open new task popup
  onNewTask(id: string) {
    this.modalService.open(id, { windowClass: 'task-modal' });
    console.log("found");
  }
  onAddTask(){
    //todo add task to database
  }
  onTaskDrop(e: any,id:string) {
    // Get the dropped data here
    this.pendingTaskList.push(e.dragData);
    //todo change task status
    this.onRemoveTask(e.dragData, this.draftTaskList);
    this.modalService.open(id, { windowClass: 'recommend-modal' }).result.then(any => {
      this.emptySelectedEmployeeArray();
    }, any => {this.emptySelectedEmployeeArray();});
  }
  onRemoveTask(task: any, list: Array<any>) {
    let index = list.map(function (e) {
      return e.taskName
    }).indexOf(task.taskName);
    list.splice(index, 1);
  }
  onSendInvitation(id: string){
    //todo  send invitation
    this.modalService.open(id, { windowClass: 'alert-modal' });
    //this.emptySelectedEmployeeArray();
  }
  emptySelectedEmployeeArray(){
    this.selectedEmployeeArray.splice(0, this.selectedEmployeeArray.length); //clear array here
  }
  toggleItemInArr(arr, item) {
    const index = arr.indexOf(item);
    index === - 1 ? arr.push(item) : arr.splice(index, 1);
  }
  addThisEmployeeToArray(employee: any, event) {
    if (!event.ctrlKey) {
      this.selectedEmployeeArray = [];
    }

    this.toggleItemInArr(this.selectedEmployeeArray, employee);
  }
  isEmployeeSelected(employee:any){
    return this.selectedEmployeeArray.indexOf(employee)!==-1;
  }

  constructor(private modalService: NgbModal) {

    //added dummy tasklist here
    //todo get task details from database
    this.draftTaskList = [{
      taskName: "create manager page",
      deadLine: "12/09/2017",
      description: "create a web page using angular, hmtl, css,etc",
      status: "",
      member: ""
    },
      {
        taskName: "create home page",
        deadLine: "15/09/2017",
        description: "create a web page using angular, hmtl, css,etc",
        status: "",
        member: ""
      },
      {
        taskName: "create home page",
        deadLine: "15/09/2017",
        description: "create a web page using angular, hmtl, css,etc",
        status: "",
        member: ""
      },
      {
        taskName: "create home page",
        deadLine: "15/09/2017",
        description: "create a web page using angular, hmtl, css,etc",
        status: "",
        member: ""
      },
      {
        taskName: "create home page",
        deadLine: "15/09/2017",
        description: "create a web page using angular, hmtl, css,etc",
        status: "",
        member: ""
      },
      {
        taskName: "create home page",
        deadLine: "15/09/2017",
        description: "create a web page using angular, hmtl, css,etc",
        status: "",
        member: ""
      },
      {
        taskName: "create home page",
        deadLine: "15/09/2017",
        description: "create a web page using angular, hmtl, css,etc",
        status: "",
        member: ""
      },
      {
        taskName: "create home page",
        deadLine: "15/09/2017",
        description: "create a web page using angular, hmtl, css,etc",
        status: "",
        member: ""
      },
      {
        taskName: "create home page",
        deadLine: "15/09/2017",
        description: "create a web page using angular, hmtl, css,etc",
        status: "",
        member: ""
      },
      {
        taskName: "create login page",
        deadLine: "17/09/2017",
        description: "create a web page using angular, hmtl, css,etc",
        status: "",
        member: ""
      }];

  }

  ngOnInit() {

  }
}

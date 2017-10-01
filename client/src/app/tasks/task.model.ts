export class Task {
  constructor() {}

  public id: string;
  public name: string;
  public description: string;
  public status: string;
  public deadline: string;
  public group: string;
  public attribute = {
    experience: "",
    interest: "",
    availability: "",
    resource: ""
  };
  public peopleRequired: string;
  public owner: string;
  public assignees: any[];
  public requestedAssignees: any[];
  public declinedAssignees: any[];
  public reasonForDeclining: string;
}

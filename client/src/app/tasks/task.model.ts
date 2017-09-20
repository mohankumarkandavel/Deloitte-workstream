export class Task {
  constructor(
    public id: string,
    public name: string,
    public description: string,
    public deadline: string,
    public group: string,
    public attribute: {
      experience:string,
      interest:string,
      availability:string,
      resource:string,
    },
    public peopleRequired: string,
  ) {}
}

export class Task {
  constructor(
    public name: string,
    public deadline: string,
    public group: string,
    public attribute: {
      experience:string,
      interest:string,
      availability:string,
      resource:string,
    },
    public availability: string,
    public peopleRequired: string,
  ) {}
}

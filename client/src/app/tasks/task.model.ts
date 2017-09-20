export class Task {
  constructor(
    public name: string,
    public deadline: string,
    public group: string,
    public resource: string,
    public availability: string,
    public peopleRequired: string,
  ) {}
}

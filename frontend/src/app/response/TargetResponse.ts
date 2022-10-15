export class TargetResponse {
  icon_id: string;
  name: string;
  amount: number;
  priority: TargetPriority;
  isSuperPriority: boolean;

  constructor(icon_id: string, name: string, amount: number, priority: TargetPriority, isSuperPriority: boolean) {
    this.icon_id = icon_id;
    this.name = name;
    this.amount = amount;
    this.priority = priority;
    this.isSuperPriority = isSuperPriority;
  }
}

enum TargetPriority {
  LOW, MIDDLE, HIGH,
}

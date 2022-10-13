import {SavingAccount} from "./saving-account";

export interface Target {
  target_id: number;
  icon_id: string;
  name: string;
  sum: number;
  amount: number;
  priority: string;
  creationDate: string;
  savingAccount: SavingAccount;
  completed: boolean;
}

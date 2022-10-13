import {SavingAccountTransaction} from "./saving-account-transaction";

export interface SavingAccount {
  percent: number;
  savingAccountTransactions: SavingAccountTransaction[];
}

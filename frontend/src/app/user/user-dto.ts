export class UserDto {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  note: string;
  roles: {
    id: number;
    role: string;
  }[];

  //admin or personal settings
  lastPasswordResetDate: number;
  iso: string;

  getName(): string {
    return this.firstName + " " + this.lastName;
  }
}

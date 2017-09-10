
import { Component, Input } from "@angular/core";
import { UserService } from "./user.service";

@Component({
  selector: 'app-title',
  templateUrl: './title.component.html',
})
export class TitleComponent {
  @Input() subtitle = '';
  title = 'Angular Modules';
  currentUser = null;

  constructor(userService: UserService) {
    this.currentUser = userService.getCurrentUser();
  }
}

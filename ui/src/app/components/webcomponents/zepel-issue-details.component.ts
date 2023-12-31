import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Integrations} from '../../shared/models/integrations.model';
import {EntityExternalMapping} from '../../models/entity-external-mapping.model';

@Component({
  selector: 'app-zepel-issue-details',
  templateUrl: './zepel-issue-details.component.html',
  styles: []
})
export class ZepelIssueDetailsComponent implements OnInit {
  @Input('application') application: Integrations;
  @Input('externalMapping') externalApplicationDetails: EntityExternalMapping;
  @Output('unLink') unLink = new EventEmitter<EntityExternalMapping>();
  public issueDetails: any;
  public issueStatus: any;
  public issueTypeDetails: any;
  public issueUserDetails: any;
  public issueId: any;

  constructor() {
  }

  ngOnInit(): void {
    this.fetchDetails();
  }

  ngOnChanges(): void {
    this.fetchDetails();
  }

  fetchDetails() {
    if (this.externalApplicationDetails.fields) {
      this.issueDetails = this.externalApplicationDetails.fields;
      this.issueId = this.issueDetails.item_id ? this.issueDetails.item_id : this.issueDetails.id;
    }
  }

  unLinkIssue(externalApplicationDetails: EntityExternalMapping) {
    this.unLink.emit(externalApplicationDetails);
  }

  htmlToPlaintext(text) {
    return text ? String(text).replace(/<[^>]+>/gm, '') : '';
  }

}

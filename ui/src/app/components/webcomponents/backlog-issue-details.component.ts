import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Integrations} from "../../shared/models/integrations.model";
import {EntityExternalMapping} from "../../models/entity-external-mapping.model";
import {IntegrationsService} from "../../shared/services/integrations.service";

@Component({
  selector: 'app-backlog-issue-details',
  templateUrl: './backlog-issue-details.component.html',
  styles: []
})
export class BackLogIssueDetailsComponent implements OnInit {
  @Input('application') application: Integrations;
  @Input('externalMapping') externalApplicationDetails: EntityExternalMapping;
  @Output('unLink') unLink = new EventEmitter<EntityExternalMapping>();
  public issueDetails: any;
  public issueStatus: any;
  public issueTypeDetails: any;
  public issueUserDetails: any;
  public issueFieldsDetails: any;

  constructor(
    private applicationService: IntegrationsService,
  ) {
  }

  ngOnInit(): void {
    this.fetchDetails();
  }
  fetchDetails() {
    this.applicationService.getBackLogIssue(this.externalApplicationDetails.applicationId, this.externalApplicationDetails.externalId)
      .subscribe(data => {
        this.issueDetails = data[0];
        this.issueTypeDetails = data[0]['issueType'];
        this.issueUserDetails = data[0]['createdUser'];
        this.issueStatus = data[0]['status'];
      }, error => console.log(error));
  }

  unLinkIssue(externalApplicationDetails: EntityExternalMapping) {
    this.unLink.emit(externalApplicationDetails);
  }

  htmlToPlaintext(text) {
    return text ? String(text).replace(/<[^>]+>/gm, '') : '';
  }

}

import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {EntityExternalMapping} from "../../models/entity-external-mapping.model";
import {EntityExternalMappingService} from "../../services/entity-external-mapping.service";
import {JiraIssueField} from "../../models/jira-issue-field.model";
import {JiraProject} from "../../models/jira-project.model";
import {JiraIssueType} from "../../models/jira-issue-type.model";
import {IntegrationsService} from "../../shared/services/integrations.service";
import {Integrations} from "../../shared/models/integrations.model";

@Component({
  selector: 'app-jira-issue-details',
  templateUrl: './jira-issue-details.component.html',
  styles: []
})
export class JiraIssueDetailsComponent implements OnInit {
  @Input('jiraIssueId') jiraIssueId: Number;
  @Input('application') application: Integrations;
  @Input('externalMapping') jiraIssueDetails: EntityExternalMapping;
  @Output('unLink') unLink = new EventEmitter<EntityExternalMapping>();
  public formFieldName: any;
  public formFields: JiraIssueField[] = [];
  public fields: any;
  public project: JiraProject;
  public issueType: JiraIssueType;

  constructor(
    private mappingService: EntityExternalMappingService,
    private applicationService: IntegrationsService
  ) {
  }

  ngOnInit(): void {
    this.fetchDetails()
  }

  ngOnChanges(): void {
    this.fetchDetails();
  }

  fetchDetails() {
    if (this.jiraIssueDetails.fields) {
      this.fields = this.jiraIssueDetails.fields;
      this.formFieldName = this.fields.names;
      this.project = this.fields.fields.project;
      this.issueType = this.fields.fields.issuetype;
      this.getFormFields(this.jiraIssueDetails.applicationId)
    }
  }

  getFormFields(workspaceId) {
    this.applicationService.getJiraFields(workspaceId).subscribe(projects => {
      let project = projects.find(project => project.id == this.project.id);
      if (project) {
        this.formFields = project.issueTypes.find(issueType => issueType.id == this.issueType.id).formFields;
      }
    });
  }

  unLinkIssue(jiraIssueDetails: EntityExternalMapping) {
    this.unLink.emit(jiraIssueDetails);
  }
}

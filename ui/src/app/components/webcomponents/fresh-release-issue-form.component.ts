import {Component, EventEmitter, Input, OnInit, Output, ViewChild, ElementRef} from '@angular/core';
import {Integrations} from "../../shared/models/integrations.model";
import {TestCaseResult} from "../../models/test-case-result.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationGuard} from "../../shared/guards/authentication.guard";
import {NotificationsService} from "angular2-notifications";
import {TranslateService} from '@ngx-translate/core';
import {ToastrService} from "ngx-toastr";
import {EntityExternalMappingService} from "../../services/entity-external-mapping.service";
import {IntegrationsService} from "../../shared/services/integrations.service";
import {MatDialog} from "@angular/material/dialog";
import {BaseComponent} from "../../shared/components/base.component";
import {EntityExternalMapping} from "../../models/entity-external-mapping.model";
import {FreshReleaseProject} from "../../models/fresh-release-project.model";
import {FreshReleaseIssueType} from "../../models/fresh-release-issue-type.model";
import { fromEvent } from 'rxjs';
import { filter, debounceTime, distinctUntilChanged, tap } from 'rxjs/operators';

@Component({
  selector: 'app-fresh-release-issue-form',
  templateUrl: './fresh-release-issue-form.component.html',
  styles: []
})
export class FreshReleaseIssueFormComponent extends BaseComponent implements OnInit {
  @Input('application') application: Integrations;
  @Input('testCaseResult') testCaseResult: TestCaseResult;
  @Output('onCreate') createCallBack = new EventEmitter<EntityExternalMapping>();
  public formFR: FormGroup;
  public projects: FreshReleaseProject[];
  public issueTypes: FreshReleaseIssueType[];
  public selectedProject: FreshReleaseProject;
  public selectedIssueType: FreshReleaseIssueType;
  public isProjectShow: boolean = false;
  public issueList: any;
  public selectedIssue: any;
  public isLinkToIssue: boolean = false;
  public isFetchingIssues: Boolean = false;
  public description: String;
  public title: String;
  public isIssueTypeShow: Boolean = false;
  public isButtonClicked: Boolean = false;

  @ViewChild('searchInput', {static: false}) searchInput: ElementRef;

  constructor(
    public authGuard: AuthenticationGuard,
    public notificationsService: NotificationsService,
    public translate: TranslateService,
    public toastrService: ToastrService,
    private mappingService: EntityExternalMappingService,
    private applicationService: IntegrationsService,
    public dialog: MatDialog) {
    super(authGuard, notificationsService, translate, toastrService)
  }

  ngOnInit(): void {
    this.applicationService.getFRProjects(this.application.id).subscribe(res => {
      this.projects = res;
      this.selectedProject = this.projects[0];
      this.fetchIssueTypes()
    })
  }

  fetchIssueTypes() {
    this.applicationService.getFRIssueTypes(this.application.id, this.selectedProject.key).subscribe(issues => {
      this.issueTypes = issues;
      this.selectedIssueType = this.issueTypes[0];
      this.initFormControl();
      this.attachSearchEvents();
      this.description="Test Case Name: " +this.testCaseResult.testCase.name + ", " +
        "\nResult URL: " + window.location.origin + "/ui/td/test_case_results/"+ this.testCaseResult.id+"\n";
    })
  }

  initFormControl() {
    this.formFR = new FormGroup({
      title: new FormControl(this.title, [
        Validators.required
      ]),
      description: new FormControl(this.description, [
        Validators.required]
      )
    });
  }

  onSubmit() {

    let mapping = new EntityExternalMapping();
    if (this.selectedIssue) {
      mapping.linkToExisting = true;
      mapping.externalId = this.selectedIssue.key;
      mapping.fields = new Map<String, Object>();
      mapping.fields["project"] = this.selectedProject.key;
      mapping.applicationId = <number>this.application.id;
    } else {
      mapping.applicationId = <number>this.application.id;
      mapping.fields = new Map<String, Object>();
      mapping.fields['title'] = this.title;
      mapping.fields['description'] = this.description;
      mapping.fields["project"] = this.selectedProject.key;
      mapping.fields["issue_type_id"] = this.selectedIssueType.id;
    }
    this.isButtonClicked = true;
    this.createCallBack.emit(mapping);
  }

  fetchIssues(term?: string) {
    this.applicationService.searchFreshReleaseIssues(this.application.id, this.selectedProject.key, term).subscribe(res => {
      this.issueList = res['issues'];
    })
  }

  toggleLinkToIssue() {
    this.isLinkToIssue = !this.isLinkToIssue;
    if (this.isLinkToIssue) {
      this.fetchIssues();
    }
  }

  setSelectedItem(setItem, selectedItem, isDropDownClose?) {
    this[setItem] = selectedItem;

    if (this.isLinkToIssue) {
      this.fetchIssues();
    }

    if (setItem == 'selectedProject') {
      this.fetchIssueTypes()
    }

    this.initFormControl()
    this.toggleDropdown(isDropDownClose)
  }

  toggleDropdown(dropDown: string) {
    this[dropDown] = !this[dropDown];
  }


  attachSearchEvents() {
    if(this.searchInput?.nativeElement){
      this.attachDebounceEvent();
      setTimeout(() => this.searchInput?.nativeElement.focus(), 10);
    }
    else
      setTimeout(()=> this.attachSearchEvents(), 100);
  }

  attachDebounceEvent() {
    fromEvent(this.searchInput.nativeElement, 'keyup')
      .pipe(
        filter(Boolean),
        debounceTime(500),
        distinctUntilChanged(),
        tap((_event: KeyboardEvent) => {
          let value;
          if (this.searchInput.nativeElement.value) {
            value = this.searchInput.nativeElement.value;
          }
          this.fetchIssues(value)
        })
      )
      .subscribe();
  }
}

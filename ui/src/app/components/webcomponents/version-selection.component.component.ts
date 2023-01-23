import {Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {WorkspaceSwitcherComponent} from "../../shared/components/webcomponents/workspace-switcher.component";
import {WorkspaceVersion} from "../../models/workspace-version.model";
import {FormControl, FormGroup} from '@angular/forms';
import {Workspace} from "../../models/workspace.model";
import {Page} from "../../shared/models/page";
import {WorkspaceType} from "../../enums/workspace-type.enum";
import {MatMenuTrigger} from "@angular/material/menu";

@Component({
  selector: 'app-version-selection',
  templateUrl: './version-selection.component.component.html',
})
export class VersionSelectionComponent extends WorkspaceSwitcherComponent implements OnInit {
  @Output('onVersionSelect') onVersionSelect= new EventEmitter<WorkspaceVersion>();
  @Input('customClass') customClass: string = '';
  @Input('showWithoutDropdown') showWithoutDropdown:boolean;

  @ViewChild('applicationViewRef', { read: TemplateRef }) applicationViewRef:TemplateRef<any>;
  @ViewChild('menuTrigger') menuTrigger: MatMenuTrigger;
  selectedProject: any;
  public workSpaceType: WorkspaceType;
  public WorkSpaceType = WorkspaceType;

  ngOnInit(): void {
    this.workSpaceType = this.version.workspace.workspaceType
    this.addControllers()
    this.fetchWorkspaces();

    this.projectSwitcherForm.valueChanges.subscribe(()=> {
      if(this.showWithoutDropdown && this.version && this.version.id != this.selectedVersion?.id) this.go();
    });
  }

  get canEnableGo() {
    return super.version?.id != this.selectedVersion?.id;
  }

  go(version?: WorkspaceVersion) {
    if(Boolean(version)){
      this.onVersionSelect.emit(version);
    } else {
      switch (this.workSpaceType) {
        case WorkspaceType.AndroidNative: {
          this.onVersionSelect.emit(this.sampleAndroidApplicationVersion)
          break;
        }
        case WorkspaceType.IOSNative: {
          this.onVersionSelect.emit(this.sampleiOSApplicationVersion)
          break;
        }
        case WorkspaceType.WebApplication: {
          this.onVersionSelect.emit(this.sampleWebApplicationVersion)
          break;
        }
        case WorkspaceType.MobileWeb: {
          this.onVersionSelect.emit(this.sampleMobileWebApplicationVersion);
          break;
        }
      }
    }
  }

  addControllers() {
    this.projectSwitcherForm = new FormGroup({
      project: new FormControl(this.version.workspace, []),
      application: new FormControl(this.version.workspace, []),
      version: new FormControl(this.version, [])
    });
  }

  get liveVersions(){
    switch (this.workSpaceType){
      case WorkspaceType.AndroidNative: {
        return this.liveAndroidVersions
        break;
      }
      case WorkspaceType.IOSNative: {
        return this.liveiOSVersions
        break;
      }
      case WorkspaceType.WebApplication: {
        return this.liveWebVersions
        break;
      }
      case WorkspaceType.MobileWeb: {
        return this.liveMobileWebVersions
        break;
      }
    }
  }

  get selectedVersion(){
    return this.projectSwitcherForm?.get('version').value;
  }
}

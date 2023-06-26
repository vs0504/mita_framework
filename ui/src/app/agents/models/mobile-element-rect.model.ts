
import {fabric} from 'fabric';
import {MobileElement} from "./mobile-element.model";

export class MobileElementRect extends fabric.Rect {
  public mobileElement: MobileElement;
  public _originalElement: MobileElement;
  public elementSelected: Boolean;
}

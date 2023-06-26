

import {Base} from "../shared/models/base.model";
import {PageObject} from "../shared/models/page-object";
import {custom, deserialize, serializable} from 'serializr';

export class Environment extends Base implements PageObject {
  @serializable
  public name: String;
  @serializable
  public description: String;
  @serializable(custom(v => v, v =>  v ))
  public parameters:  JSON;
  public projectId: Number;
  public selected: Boolean;
  public parametersJson: string;

  deserialize(input: any): this {
    return Object.assign(this, deserialize(Environment, input));
  }

}

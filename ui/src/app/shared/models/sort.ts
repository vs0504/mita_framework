

import {Deserializable} from "app/shared/models/deserializable";
import {deserialize, serializable} from "serializr";

export class Sort implements Deserializable {
  @serializable
  public sorted: boolean;

  deserialize(input: any): this {
    return Object.assign(this, deserialize(Sort, input));
  }
}

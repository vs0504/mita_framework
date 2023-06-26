
import {Deserializable} from "../../shared/models/deserializable";
import {deserialize, primitive, serializable} from "serializr";

export class MirroringResponse implements Deserializable {
  @serializable(primitive())
  webServerPort: Number;

  deserialize(input: any): this {
    return Object.assign(this, deserialize(MirroringResponse, input));
  }
}

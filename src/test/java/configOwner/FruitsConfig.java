package configOwner;

import org.aeonbits.owner.Config;

import java.util.List;

public interface FruitsConfig extends Config {

    @Key("array")
    String[] getFruitsArray();

    @Key("defaultArray")
    @DefaultValue("orange,banana")
    String[] getFruitsArrayWithDefaultValues();


    @Key("list")
    List<String> getFruitsList();

    @Key("listSeparator")
    @Separator(";")
    @DefaultValue("orange;banana")
    List<String> getFruitsListSeparatorWithDefaultValues();

}

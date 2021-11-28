module wishList.core {
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    exports wishList.core;
    exports wishList.json;
    exports wishList.utils;

    opens wishList.core;
    opens wishList.json;
    opens wishList.utils;
}

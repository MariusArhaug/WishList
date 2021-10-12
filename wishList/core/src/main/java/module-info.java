module wishList.core {
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    exports wishList.core;
    exports wishList.json;

    opens wishList.core;
}

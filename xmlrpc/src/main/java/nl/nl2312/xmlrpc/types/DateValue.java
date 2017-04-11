package nl.nl2312.xmlrpc.types;

import nl.nl2312.xmlrpc.deserialization.DeserializationContext;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.stream.OutputNode;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

@Root
public final class DateValue implements Value {

    public static final String CODE = "dateTime.iso8601";

    private static final DateFormat SIMPLE_ISO8601 = new Iso8601DateFormat();

    @Element(name = CODE)
    Date value;

    public DateValue(Date from) {
        this.value = from;
    }

    @Override
    public Date value() {
        return value;
    }

    @Override
    public void write(OutputNode node) throws Exception {
        OutputNode child = node.getChild(CODE);
        child.setValue(SIMPLE_ISO8601.format(value));
    }

    @Override
    public Object asObject(DeserializationContext context, Class<?> type, Class<?> param) throws IllegalAccessException, InstantiationException {
        return value;
    }

    public static DateValue parse(String value) throws ParseException {
        return new DateValue(SIMPLE_ISO8601.parse(value));
    }

}

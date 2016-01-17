package pt.ipg.ei.cloud.menu.conversion

import pt.ipg.ei.cloud.menu.entities.History
import pt.ipg.ei.cloud.menu.entities.HistoryType

class DefaultConversion {
    private static Map getProperties(source) {
        return source.properties.findAll { it.key != 'class' }
    }

    static <S, D> D convert(S source, D destiny) {
        for (property in getProperties(source)) {
            destiny[property.key] = property.value
        }
        return destiny
    }

    static <S> History createHistory(S source, HistoryType historyType) {
        History history = new History();
        for (property in getProperties(source)) {
            if (property.value instanceof Enum) {
                history.map[property.key] = property.value.toString()
            } else {
                history.map[property.key] = property.value
            }
        }
        history.historyType = historyType
        history.entity = source.class.canonicalName
        return history
    }
}

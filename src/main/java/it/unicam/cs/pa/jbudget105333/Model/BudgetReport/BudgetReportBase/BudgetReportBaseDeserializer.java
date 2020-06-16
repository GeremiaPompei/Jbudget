package it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReportBase;

import com.google.gson.*;
import it.unicam.cs.pa.jbudget105333.Model.Budget.Budget;
import it.unicam.cs.pa.jbudget105333.Model.Budget.BudgetBase.BudgetBaseDeserializer;
import it.unicam.cs.pa.jbudget105333.Model.BudgetReport.BudgetReport;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.Ledger;
import it.unicam.cs.pa.jbudget105333.Model.Ledger.LedgerBase.LedgerBaseDeserializer;

import java.lang.reflect.Type;
import java.util.logging.Logger;

/**
 * Classe responsabile della deserializzazione di un BudgetReportBase.
 */
public class BudgetReportBaseDeserializer implements JsonDeserializer<BudgetReport> {

    /**
     * Variabile utile alla gestione del log del BudgetReportBaseDeserializer.
     */
    private static final Logger logger = Logger.getGlobal();

    /**
     * Metodo responsabile della deserializzazione deli un BudgetReportBase.
     * @param json JsonElement da deserializzare.
     * @param typeOfT Tipo dell'oggetto in cui deserializzare.
     * @param context Contesto di deserializzazione.
     * @return BudgetReport deserializzato.
     * @throws JsonParseException Errore del formato del file json da deserializzare.
     */
    @Override
    public BudgetReport deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        this.logger.finer("Start deserialization.");
        JsonObject jo = json.getAsJsonObject();
        Ledger ledger = new LedgerBaseDeserializer().deserialize(jo.get("Ledger"),Ledger.class,context);
        Budget budget = new BudgetBaseDeserializer(ledger).deserialize(jo.get("Budget"),Budget.class,context);
        this.logger.finer("Stop deserializzation.");
        return new BudgetReportBase(ledger,budget);
    }
}

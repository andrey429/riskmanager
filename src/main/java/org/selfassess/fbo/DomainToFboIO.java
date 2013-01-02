package org.selfassess.fbo;

import org.apache.log4j.Logger;
import org.selfassess.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 02.01.13
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
public class DomainToFboIO {

    public DomainToFboIO() {
    }

    public EV1FormBackingObject convertToFormBackingObject(EV1Model ev1Model) {
        return null;

    }




    public EV1Model convertToDomainObject(EV1FormBackingObject ev1FormBackingObject) {


        EV1Model ev1Model = new EV1Model();
        NumbersList groupValues = new NumbersList();
        ListOfList paramValues = new ListOfList();

        List<NumbersListEntry> listOfGroupVal = new ArrayList<NumbersListEntry>();
        List<ListOfListEntry> listOfGroups = new ArrayList<ListOfListEntry>();

        //fill the param values
        for (int i = 0; i < 10; i++) {
            ListOfListEntry lle = new ListOfListEntry();
            List<NumbersListEntry> listOfParamValues = new ArrayList<NumbersListEntry>();

            lle.setEntryOrder(i);
            NumbersList group = new NumbersList();

            for (int j = 0; j < ev1FormBackingObject.getParameterValues().get(i).size(); j++) {
                NumbersListEntry entry = new NumbersListEntry();
                entry.setEntry(j);
                entry.setValue(ev1FormBackingObject.getParameterValues().get(i).get(j));
                listOfParamValues.add(entry);
            }

            group.setNumbersListEntries(listOfParamValues);
            lle.setNumbersList(group);

            listOfGroups.add(lle);
        }

        paramValues.setListOfListEntries(listOfGroups);

        //fill the group values

        Logger logger = Logger.getLogger("controller");


        for (int i = 0; i < 10; i++) {
            NumbersListEntry nle = new NumbersListEntry();
            nle.setEntry(i);
            nle.setValue(ev1FormBackingObject.getmGroupValues().get(i));

            logger.debug("M"+(i+1)+" val :"+ ev1FormBackingObject.getmGroupValues().get(i));

            listOfGroupVal.add(nle);
        }


        groupValues.setNumbersListEntries(listOfGroupVal);

        ev1Model.setmGroupValues(groupValues);
        ev1Model.setEv1value(ev1FormBackingObject.getEv1Value());
        ev1Model.setmParamValues(paramValues);

        return ev1Model;
    }



}

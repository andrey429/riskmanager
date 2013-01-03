package org.selfassess.utils;

import org.apache.log4j.Logger;
import org.selfassess.domain.*;
import org.selfassess.fbo.EV1FormBackingObject;
import org.selfassess.utils.EV1ValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 02.01.13
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
public class POJO2FBOConverter {

    private Calculator calculator;

    public POJO2FBOConverter() {
        calculator = new Calculator();
    }

    public EV1FormBackingObject convertToFormBackingObject(EV1Model ev1Model) {
        EV1FormBackingObject ev1FormBackingObject = new EV1FormBackingObject(true);

        //filling groupValues
        NumbersList groupValues = ev1Model.getmGroupValues();
        List<NumbersListEntry> listOfEntries = groupValues.getNumbersListEntries();
        List<Double> fboGroupValues = new ArrayList<Double>();//will store the result

        for (NumbersListEntry nle : listOfEntries) {
            fboGroupValues.add(nle.getEntry(), nle.getValue());
        }
        //filling paramValues
        ListOfList paramValues = ev1Model.getmParamValues();
        List<ListOfListEntry> listOfListEntries = paramValues.getListOfListEntries();//depth 1
        List<ArrayList<Double>> fboParamValues = new ArrayList<ArrayList<Double>>();


        for (ListOfListEntry lle : listOfListEntries) {
            NumbersList groupOfParams = lle.getNumbersList();
            List<NumbersListEntry> tempListOfEntries = groupOfParams.getNumbersListEntries();//depth 2
            ArrayList<Double> tempListOfValues = new ArrayList<Double>();
            for (NumbersListEntry nle : tempListOfEntries) {
                tempListOfValues.add(nle.getEntry(), nle.getValue());//filling temporary list
            }
            fboParamValues.add(lle.getEntryOrder(), tempListOfValues);//putting list on place in list

        }

        ev1FormBackingObject.setEv1Value(ev1Model.getEv1value());
        ev1FormBackingObject.setmGroupValues(fboGroupValues);
        ev1FormBackingObject.setParameterValues(fboParamValues);

        return ev1FormBackingObject;

    }


    public EV1Model convertToDomainObject(EV1FormBackingObject ev1FormBackingObject) {

        //first get the ev1 and group params recalculated
        calculator.recalculateEV1GroupParameters(ev1FormBackingObject);

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
                listOfParamValues.add(j, entry);
            }

            group.setNumbersListEntries(listOfParamValues);
            lle.setNumbersList(group);

            listOfGroups.add(i, lle);
        }

        paramValues.setListOfListEntries(listOfGroups);

        //fill the group values

        Logger logger = Logger.getLogger("controller");


        for (int i = 0; i < 10; i++) {
            NumbersListEntry nle = new NumbersListEntry();
            nle.setEntry(i);
            nle.setValue(ev1FormBackingObject.getmGroupValues().get(i));

            logger.debug("M" + (i + 1) + " val :" + ev1FormBackingObject.getmGroupValues().get(i));

            listOfGroupVal.add(i, nle);
        }


        groupValues.setNumbersListEntries(listOfGroupVal);

        ev1Model.setmGroupValues(groupValues);
        ev1Model.setEv1value(ev1FormBackingObject.getEv1Value());
        ev1Model.setmParamValues(paramValues);

        return ev1Model;
    }




}

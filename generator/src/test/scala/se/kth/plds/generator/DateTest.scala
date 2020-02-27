package se.kth.plds.generator

import org.scalatest._
import org.scalatest.funsuite._
import org.scalatest.matchers.should._

class DateTest extends AnyFunSuite with Matchers {
  test("Test Simple Date conversion") {
    import Utils.DateString;

    val dateS = "2020-03-05 13:00";
    val date = Utils.convertStringToDate(dateS);
    val dateS2 = Utils.getDateAsString(date);
    dateS2 should equal(dateS);

    val implicitDate = dateS.toDate;
    val dateS3 = Utils.getDateAsString(implicitDate);
    dateS3 should equal(dateS);

    val confDateS = "05 13:00";
    val implicitConfDate = confDateS.toConfDate;
    val dateS4 = Utils.getDateAsString(implicitConfDate);
    dateS4 should equal(dateS);

    val dateS6 = "2020-03-06 09:00";
    val confDateS6 = "06 09:00";
    val implicitConfDate6 = confDateS6.toConfDate;
    val dateS62 = Utils.getDateAsString(implicitConfDate6);
    dateS62 should equal(dateS6);
  }

  test("Test Date addition") {
    import Utils.{AddableDate, DateString};

    val dateS = "06 09:00";
    val date = dateS.toConfDate;
    val later = date.addMinutes(30);
    val laterS = Utils.getDateAsString(later);
    laterS should equal("2020-03-06 09:30");
  }
}

package se.kth.plds.generator

import java.text.SimpleDateFormat
import java.util.{Calendar, Date, GregorianCalendar}
import scala.language.implicitConversions

object Utils {

  val DATE_FORMAT = "yyyy-MM-dd HH:mm";
  val CONFERENCE_DATE_FORMAT = "dd HH:mm";
  val TIME_FORMAT = "HH:mm";

  def getDateAsString(d: Date): String = {
    val dateFormat = new SimpleDateFormat(DATE_FORMAT);
    dateFormat.format(d)
  }

  def getTimeAsString(d: Date): String = {
    val dateFormat = new SimpleDateFormat(TIME_FORMAT);
    dateFormat.format(d)
  }

  def convertStringToDate(s: String): Date = {
    val dateFormat = new SimpleDateFormat(DATE_FORMAT);
    dateFormat.parse(s)
  }

  def convertStringToConferenceDate(s: String): Date = {
    val dateFormat = new SimpleDateFormat(CONFERENCE_DATE_FORMAT);
    val date = dateFormat.parse(s);
    val cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.YEAR, 2020);
    cal.set(Calendar.MONTH, Calendar.MARCH);
    cal.getTime()
  }

  implicit class DateString(s: String) {
    def toDate: Date = convertStringToDate(s);
    def toConfDate: Date = convertStringToConferenceDate(s);
  }

  implicit class AddableDate(d: Date) {
    def addMinutes(min: Int): Date = {
      val cal = Calendar.getInstance();
      cal.setTime(d);
      cal.add(Calendar.MINUTE, min);
      cal.getTime()
    }
  }

}

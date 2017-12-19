/**
 * Copyright (C) 2010-14 diirt developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.phoebus.util.time;

import static java.time.Duration.between;
import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;

import org.junit.Assert;
import org.junit.Test;
import org.phoebus.util.time.TimeInterval;
import org.phoebus.util.time.TimeParser;
/**
 *
 * @author shroffk
 */
public class TimeParserTest {

    @Test
    public void getNow() {
        Instant ts = TimeParser.getInstant("now");
        Assert.assertTrue("Failed to obtain Timestamp corresponding to now ",
                ts != null && ts instanceof Instant);
    }

    /**
     * Test the times Duration ← relative
     */
    @Test
    public void getDuration() {
        // "last min", "last hour", "last day", "last week"
        Duration lastMin = TimeParser.getDuration("last min");
        Assert.assertEquals("Failed to get Duration for last min", Duration.ofSeconds(60),
                lastMin);
        Duration lastHour = TimeParser.getDuration("last hour");
        Assert.assertEquals("Failed to get Duration for last hour",
                Duration.ofHours(1), lastHour);

        // "last 5 mins", "last 5 hours", "last 5 days", "last 5 weeks"
        TemporalAmount last5Min = TimeParser.getDuration("last 5 mins");
        Assert.assertEquals("Failed to get Duration for last 5 mins",
                Duration.ofMinutes(5), last5Min);
        TemporalAmount last5Hour = TimeParser.getDuration("last 5 hours");
        Assert.assertEquals("Failed to get Duration for last 5 hours",
                Duration.ofHours(5), last5Hour);
//        Duration last5Day = TimeParser.getDuration("last 5 days");
//        Assert.assertEquals("Failed to get Duration for last 5 days",
//                60 * 60 * 24 * 5, last5Day.getSeconds());
//        Duration last5Week = TimeParser.getDuration("last 5 weeks");
//        Assert.assertEquals("Failed to get Duration for last 5 weeks", 60
//                * 60 * 24 * 7 * 5, last5Week.getSeconds());

        // "1 min ago", "1 hours ago", "1 days ago", "1 weeks ago"
        TemporalAmount oneMinAgo = TimeParser.getDuration("1 min ago");
        Assert.assertEquals("Failed to get Duration for 1 min ago", 
                Duration.ofMinutes(1),
                oneMinAgo);
        TemporalAmount oneHourAgo = TimeParser.getDuration("1 hour ago");
        Assert.assertEquals("Failed to get Duration for 1 hour ago",
                Duration.ofHours(1), oneHourAgo);
//        Duration oneDayAgo = TimeParser.getDuration("1 day ago");
//        Assert.assertEquals("Failed to get Duration for 1 days ago",
//                60 * 60 * 24, oneDayAgo.getSeconds());
//        Duration oneWeekAgo = TimeParser.getDuration("1 week ago");
//        Assert.assertEquals("Failed to get Duration for 1 week ago",
//                60 * 60 * 24 * 7, oneWeekAgo.getSeconds());

        // "5 mins ago", "5 hours ago", "5 days ago", "5 weeks ago"
        TemporalAmount fiveMinsAgo = TimeParser.getDuration("5 mins ago");
        Assert.assertEquals("Failed to get Duration for 5 mins ago",
                Duration.ofMinutes(5),
                fiveMinsAgo);
        TemporalAmount fiveHoursAgo = TimeParser.getDuration("5 hours ago");
        Assert.assertEquals("Failed to get Duration for 5 hours ago",
                Duration.ofHours(5),
                fiveHoursAgo);
//        Duration fiveDaysAgo = TimeParser.getDuration("5 days ago");
//        Assert.assertEquals("Failed to get Duration for 5 days ago",
//                60 * 60 * 24 * 5, fiveDaysAgo.getSeconds());
//        Duration fiveWeeksAgo = TimeParser.getDuration("5 weeks ago");
//        Assert.assertEquals("Failed to get Duration for 5 week ago", 60
//                * 60 * 24 * 7 * 5, fiveWeeksAgo.getSeconds());

        // Check case insensitivity Last 4 Mins, Last 4 Hours, Last 4 Days, Last
        // 4 WEEKS
        TemporalAmount last4Min = TimeParser.getDuration("Last 4 Mins");
        Assert.assertEquals("Failed to get Duration for Last 4 Mins",
                Duration.ofMinutes(4),
                last4Min);
        TemporalAmount last4Hour = TimeParser.getDuration("Last 4 Hours");
        Assert.assertEquals("Failed to get Duration for Last 4 Hours",
                Duration.ofHours(4),
                last4Hour);
//        Duration last4Day = TimeParser.getDuration("Last 4 Day");
//        Assert.assertEquals("Failed to get Duration for Last 4 Day",
//                60 * 60 * 24 * 4, last4Day.getSeconds());
//        Duration last4Week = TimeParser.getDuration("Last 4 WEEKS");
//        Assert.assertEquals("Failed to get Duration for Last 4 WEEKS", 60
//                * 60 * 24 * 7 * 4, last4Week.getSeconds());

        // Check incorrect units in terms of plurality last 3 min, last 3 hour,
        // last 3 day, last 3 week
        TemporalAmount last3Min = TimeParser.getDuration("last 3 min");
        Assert.assertEquals("Failed to get Duration for last 3 min",
                Duration.ofMinutes(3),
                last3Min);
        TemporalAmount last3Hour = TimeParser.getDuration("last 3 hour");
        Assert.assertEquals("Failed to get Duration for last 3 hour",
                Duration.ofHours(3),
                last3Hour);
//        Duration last3Day = TimeParser.getDuration("last 3 day");
//        Assert.assertEquals("Failed to get Duration for last 3 day",
//                60 * 60 * 24 * 3, last3Day.getSeconds());
//        Duration last3Week = TimeParser.getDuration("last 3 week");
//        Assert.assertEquals("Failed to get Duration for last 3 week", 60
//                * 60 * 24 * 7 * 3, last3Week.getSeconds());

        // Check missing space between time quantity and unit last 2mins, last
        // 2hours, last 2days, last 2weeks, 2mins ago, 2hours ago, 2days ago,
        // 2weeks ago
        TemporalAmount last2Mins = TimeParser.getDuration("last 2mins");
        Assert.assertEquals("Failed to get Duration for last 2mins",
                Duration.ofMinutes(2), last2Mins);
        TemporalAmount last2Hours = TimeParser.getDuration("last 2hours");
        Assert.assertEquals("Failed to get Duration for last 2hours",
                Duration.ofHours(2), last2Hours);
//        Duration last2Days = TimeParser.getDuration("last 2days");
//        Assert.assertEquals("Failed to get Duration for last 2days",
//                60 * 60 * 24 * 2, last2Days.getSeconds());
//        Duration last2Weeks = TimeParser.getDuration("last 2weeks");
//        Assert.assertEquals("Failed to get Duration for last 2weeks", 60
//                * 60 * 24 * 7 * 2, last2Weeks.getSeconds());
        TemporalAmount twoMinsAgo = TimeParser.getDuration("2mins ago");
        Assert.assertEquals("Failed to get Duration for 2mins ago",
                Duration.ofMinutes(2),
                twoMinsAgo);
        TemporalAmount twoHoursAgo = TimeParser.getDuration("2hours ago");
        Assert.assertEquals("Failed to get Duration for 2hours ago",
                Duration.ofHours(2),
                twoHoursAgo);
//        Duration twoDaysAgo = TimeParser.getDuration("2days ago");
//        Assert.assertEquals("Failed to get Duration for 2days ago",
//                60 * 60 * 24 * 2, twoDaysAgo.getSeconds());
//        Duration twoWeeksAgo = TimeParser.getDuration("2weeks ago");
//        Assert.assertEquals("Failed to get Duration for 2weeks ago", 60
//                * 60 * 24 * 7 * 2, twoWeeksAgo.getSeconds());

    }

    /**
     * Test the times TimeInterval ← relative
     */
    @Test
    public void getTimeInterval() {
        // "last min", "last hour", "last day", "last week"
        TimeInterval lastMin = TimeParser.getTimeInterval("last min");
        Assert.assertEquals("Failed to get TimeInterval for last min", 60,
                between(lastMin.getStart(), lastMin.getEnd()).getSeconds());
        TimeInterval lastHour = TimeParser.getTimeInterval("last hour");
        Assert.assertEquals("Failed to get TimeInterval for last hour",
                (60 * 60),
                between(lastHour.getStart(), lastHour.getEnd()).getSeconds(), 0);
        TimeInterval lastDay = TimeParser.getTimeInterval("last day");
        Assert.assertEquals(
                "Failed to get TimeInterval for last day",
                (60 * 60 * 24),
                between(lastDay.getStart(), lastDay.getEnd()).getSeconds(),
                0);

        // "last 5 mins", "last 5 hours", "last 5 days", "last 5 weeks"
        TimeInterval last5Min = TimeParser.getTimeInterval("last 5 mins");
        Assert.assertEquals("Failed to get TimeInterval for last 5 mins",
                (60 * 5),
                between(last5Min.getStart(), last5Min.getEnd())
                        .getSeconds(), 0);
        TimeInterval last5Hour = TimeParser.getTimeInterval("last 5 hours");
        Assert.assertEquals("Failed to get TimeInterval for last 5 hours",
                (60 * 60 * 5),
                between(last5Hour.getStart(), last5Hour.getEnd())
                        .getSeconds(), 0);
        TimeInterval last5Day = TimeParser.getTimeInterval("last 5 days");
        Assert.assertEquals("Failed to get TimeInterval for last 5 days",
                (60 * 60 * 24 * 5),
                between(last5Day.getStart(), last5Day.getEnd())
                        .getSeconds(), 0);

        // "1 min ago", "1 hours ago", "1 days ago", "1 weeks ago"
        TimeInterval oneMinAgo = TimeParser.getTimeInterval("1 min ago");
        Assert.assertEquals("Failed to get TimeInterval for 1 min ago", (60),
                between(oneMinAgo.getStart(), oneMinAgo.getEnd())
                        .getSeconds(), 0);
        TimeInterval oneHourAgo = TimeParser.getTimeInterval("1 hour ago");
        Assert.assertEquals("Failed to get TimeInterval for 1 hour ago",
                (60 * 60),
                between(oneHourAgo.getStart(), oneHourAgo.getEnd())
                        .getSeconds(), 0);
        TimeInterval oneDayAgo = TimeParser.getTimeInterval("1 day ago");
        Assert.assertEquals("Failed to get TimeInterval for 1 days ago",
                (60 * 60 * 24),
                between(oneDayAgo.getStart(), oneDayAgo.getEnd())
                        .getSeconds(), 0);

        // "5 mins ago", "5 hours ago", "5 days ago", "5 weeks ago"
        TimeInterval fiveMinsAgo = TimeParser.getTimeInterval("5 mins ago");
        Assert.assertEquals("Failed to get TimeInterval for 5 mins ago",
                (60 * 5),
                between(fiveMinsAgo.getStart(), fiveMinsAgo.getEnd())
                        .getSeconds(), 0);
        TimeInterval fiveHoursAgo = TimeParser.getTimeInterval("5 hours ago");
        Assert.assertEquals("Failed to get TimeInterval for 5 hours ago",
                (60 * 60 * 5),
                between(fiveHoursAgo.getStart(), fiveHoursAgo.getEnd())
                        .getSeconds(), 0);
        TimeInterval fiveDaysAgo = TimeParser.getTimeInterval("5 days ago");
        Assert.assertEquals("Failed to get TimeInterval for 5 days ago",
                (60 * 60 * 24 * 5),
                between(fiveDaysAgo.getStart(), fiveDaysAgo.getEnd())
                        .getSeconds(), 0);

        // Check case insensitivity Last 4 Mins, Last 4 Hours, Last 4 Days, Last
        // 4 WEEKS
        TimeInterval last4Min = TimeParser.getTimeInterval("Last 4 Mins");
        Assert.assertEquals("Failed to get TimeInterval for Last 4 Mins",
                (60 * 4),
                between(last4Min.getStart(), last4Min.getEnd())
                        .getSeconds(), 0);
        TimeInterval last4Hour = TimeParser.getTimeInterval("Last 4 Hours");
        Assert.assertEquals("Failed to get TimeInterval for Last 4 Hours",
                (60 * 60 * 4),
                between(last4Hour.getStart(), last4Hour.getEnd())
                        .getSeconds(), 0);
        TimeInterval last4Day = TimeParser.getTimeInterval("Last 4 Day");
        Assert.assertEquals("Failed to get TimeInterval for Last 4 Day",
                (60 * 60 * 24 * 4),
                between(last4Day.getStart(), last4Day.getEnd())
                        .getSeconds(), 0);

        // Check incorrect units in terms of plurality last 3 min, last 3 hour,
        // last 3 day, last 3 week
        TimeInterval last3Min = TimeParser.getTimeInterval("last 3 min");
        Assert.assertEquals("Failed to get TimeInterval for last 3 min",
                (60 * 3),
                between(last3Min.getStart(), last3Min.getEnd())
                        .getSeconds(), 0);
        TimeInterval last3Hour = TimeParser.getTimeInterval("last 3 hour");
        Assert.assertEquals("Failed to get TimeInterval for last 3 hour",
                (60 * 60 * 3),
                between(last3Hour.getStart(), last3Hour.getEnd())
                        .getSeconds(), 0);
        TimeInterval last3Day = TimeParser.getTimeInterval("last 3 day");
        Assert.assertEquals("Failed to get TimeInterval for last 3 day",
                (60 * 60 * 24 * 3),
                between(last3Day.getStart(), last3Day.getEnd())
                        .getSeconds(), 0);

        // Check missing space between time quantity and unit last 2mins, last
        // 2hours, last 2days, last 2weeks, 2mins ago, 2hours ago, 2days ago,
        // 2weeks ago
        TimeInterval last2Mins = TimeParser.getTimeInterval("last 2mins");
        Assert.assertEquals("Failed to get TimeInterval for last 2mins",
                (60 * 2),
                between(last2Mins.getStart(), last2Mins.getEnd())
                        .getSeconds(), 0);
        TimeInterval last2Hours = TimeParser.getTimeInterval("last 2hours");
        Assert.assertEquals("Failed to get TimeInterval for last 2hours",
                (60 * 60 * 2),
                between(last2Hours.getStart(), last2Hours.getEnd())
                        .getSeconds(), 0);
        TimeInterval last2Days = TimeParser.getTimeInterval("last 2days");
        Assert.assertEquals("Failed to get TimeInterval for last 2days",
                (60 * 60 * 24 * 2),
                between(last2Days.getStart(), last2Days.getEnd())
                        .getSeconds(), 0);
        TimeInterval twoMinsAgo = TimeParser.getTimeInterval("2mins ago");
        Assert.assertEquals("Failed to get TimeInterval for 2mins ago",
                (60 * 2),
                between(twoMinsAgo.getStart(), twoMinsAgo.getEnd())
                        .getSeconds(), 0);
        TimeInterval twoHoursAgo = TimeParser.getTimeInterval("2hours ago");
        Assert.assertEquals("Failed to get TimeInterval for 2hours ago",
                (60 * 60 * 2),
                between(twoHoursAgo.getStart(), twoHoursAgo.getEnd())
                        .getSeconds(), 0);
        TimeInterval twoDaysAgo = TimeParser.getTimeInterval("2days ago");
        Assert.assertEquals("Failed to get TimeInterval for 2days ago",
                (60 * 60 * 24 * 2),
                between(twoDaysAgo.getStart(), twoDaysAgo.getEnd())
                        .getSeconds(), 0);
    }

    /**
     * Test the creation of time intervals using from: and to:
     */
    @Test
    public void getAbsoluteTimeInterval() {
        TimeInterval oneMin = TimeParser.getTimeInterval("3 mins ago",
                "2 mins ago");
        Assert.assertEquals(
                "Failed to get time Interval for String: from:3 mins ago to:2 mins ago",
                60,
                between(oneMin.getStart(), oneMin.getEnd()).getSeconds(), 0);
        // Explicitly define the time
        String startTime = "1976-01-01T00:00:00";
        String endTime = "1976-01-02T00:00:00";
        TimeInterval oneDay = TimeParser.getTimeInterval(startTime, endTime);
        Assert.assertEquals("Failed to get time Interval for String: from:"
                + startTime + " to:" + endTime, 60 * 60 * 24,
                between(oneDay.getStart(), oneDay.getEnd()).getSeconds(), 0);

    }

    /**
     * Test time strings i.e. 4 hours 3 mins 2 secs ago
     */
    @Test
    public void parseCompositeTimeString() {
        TemporalAmount last5Mins30Secs = TimeParser.getDuration("last 5 mins 30 secs");
        Assert.assertEquals("Failed to get Duration for last 5 mins",
                Duration.ofMinutes(5).plusSeconds(30), last5Mins30Secs);
        TemporalAmount last3Hours5Mins30Secs = TimeParser.getDuration("last 3 hours 5 mins 30 secs");
        Assert.assertEquals("Failed to get Duration for last 5 mins",
                Duration.ofHours(3).plusMinutes(5).plusSeconds(30), last3Hours5Mins30Secs);
    }
    

    /**
     * Test the creation prasing of string representations of time to create {@link TimeRelativeInterval}
     *
     * The below tests create an interval which represents a single month.
     * 
     */
    @Test
    public void parseRelativeInterval() {
        // Create an interval for January
        TimeRelativeInterval interval = TimeParser.getTimeRelativeInterval("1 months ago", "0 month ago");
        
        // Check jan it is 31 days
        TimeInterval jan = interval.toAbsoluteInterval(LocalDateTime.parse("2011-02-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toInstant(ZoneOffset.UTC));
        assertEquals(31L, Duration.between(jan.getStart(), jan.getEnd()).toDays());

        // Check February is 28 days
        TimeInterval feb = interval.toAbsoluteInterval(LocalDateTime.parse("2011-03-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toInstant(ZoneOffset.UTC));
        assertEquals(28L, Duration.between(feb.getStart(), feb.getEnd()).toDays());

        // Check February is 29 days because it is a leap year
        TimeInterval leapFeb = interval.toAbsoluteInterval(LocalDateTime.parse("2012-03-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME).toInstant(ZoneOffset.UTC));
        assertEquals(29L, Duration.between(leapFeb.getStart(), leapFeb.getEnd()).toDays());
    }
}
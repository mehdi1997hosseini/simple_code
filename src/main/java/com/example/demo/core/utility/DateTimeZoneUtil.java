package com.example.demo.core.utility;

import java.time.Duration;
import java.time.Instant;

public abstract class DateTimeZoneUtil {

    private DateTimeZoneUtil() {
    }

    /**
     * @Description
     * @EN <p> This inner class performs date-related operations. </p>
     * @FA <p>این کلاس داخلی عملیات های مربوط به تاریخ را انجام میدهد </p>
     */
    public static class DateUtil {
        private DateUtil() {
        }


    }

    /**
     * @Description
     * @EN <p> This inner class performs time-related operations. </p>
     * @FA <p>این کلاس داخلی عملیات های مربوط به زمان را انجام میدهد </p>
     */
    public static class TimeUtil {
        private TimeUtil() {
        }


    }

    public static class DurationAndInstantUtils {
        private DurationAndInstantUtils() {
        }

        public static Instant calculateExpiry(long expiresIn, TimeUnitType timeUnit, double refreshTriggerRatio) {
            return calculateExpiry(expiresIn, timeUnit, refreshTriggerRatio, false);
        }

        public static Instant calculateExpiry(long expiresIn, TimeUnitType timeUnit, double refreshTriggerRatio, boolean fromStart) {
            Duration duration = getDurationOfTimeByTimeUnitType(expiresIn, timeUnit);
            Duration offset = calculateOffset(duration, refreshTriggerRatio, fromStart);
            return Instant.now().plus(offset);
        }

        public static Instant calculateExpiry(Instant expiresAt, double refreshTriggerRatio, boolean fromStart) {
            // اختلاف زمانی از زمان کنونی تا زمانی که توکن expire میشود
            Duration duration = Duration.between(Instant.now(), expiresAt);
            return Instant.now().plus(calculateOffset(duration, refreshTriggerRatio, fromStart));
        }

        public static Instant calculateExpiry(Instant expiresAt, double refreshTriggerRatio) {
            return calculateExpiry(expiresAt, refreshTriggerRatio, false);
        }

        public static Duration getDurationOfTimeByTimeUnitType(long expiresIn, TimeUnitType timeUnit) {
            return switch (timeUnit) {
                case NANOSECONDS -> Duration.ofNanos(expiresIn);
                case MICROSECONDS -> Duration.ofNanos(expiresIn * 1000);
                case MILLISECONDS -> Duration.ofMillis(expiresIn);
                case SECONDS -> Duration.ofSeconds(expiresIn);
                case MINUTES -> Duration.ofMinutes(expiresIn);
                case HOURS -> Duration.ofHours(expiresIn);
                case DAYS -> Duration.ofDays(expiresIn);
                case CUSTOM_SECONDS -> Duration.ofSeconds(expiresIn);
                case CUSTOM_MINUTES -> Duration.ofMinutes(expiresIn);
                case CUSTOM_HOURS -> Duration.ofHours(expiresIn);
                default -> throw new IllegalArgumentException("Unsupported TimeUnit: " + timeUnit);
            };
        }

        /**
         * @Description
         * @EN Calculate the end time based on the amount of input that is as a percentage of time. Now we can consider the time from the beginning or from the end.
         * @FA : محاسبه زمان پایان بر اساس مقدار ورودی که به عنوان درصدی از زمان است حال میتواند از ابتدا زمان در نظر بگیریم و یا اینکه از انتها
         * @Param duration زمان مورد نظر
         * @Param ration درصد ای عدد صحیح که چقدر از آن زمان مورد نظر محسابه شود
         * @Param fromStart از زمان شروع => true | از انتهای زمان / گذشته از شروع زمان false
         */
        private static Duration calculateOffset(Duration duration, double ratio, boolean fromStart) {
            long totalNanos = duration.toNanos();
            long offsetNanos;
            if (ratio < 1) {
                offsetNanos = (long) (fromStart ? totalNanos * ratio : totalNanos * (1 - ratio));
            } else {
                offsetNanos = (long) (fromStart ? totalNanos * (ratio / 100) : totalNanos * (1 - (ratio / 100)));
            }
            return Duration.ofNanos(offsetNanos);
        }

        public static Duration toDurationFromInstant(Instant futureInstant) {
            Instant now = Instant.now();
            return Duration.between(now, futureInstant);
        }


        public static Instant convertToInstant(long value, TimeUnitType unit) {
            return switch (unit) {
                case NANOSECONDS -> Instant.ofEpochSecond(0, value);
                case MICROSECONDS -> Instant.ofEpochMilli(value / 1000);
                case MILLISECONDS -> Instant.ofEpochMilli(value);
                case SECONDS -> Instant.ofEpochSecond(value);
                case MINUTES -> Instant.ofEpochSecond(value * 60);
                case HOURS -> Instant.ofEpochSecond(value * 3600);
                case DAYS -> Instant.ofEpochSecond((value * 3600) * 24);
                default -> throw new IllegalArgumentException("Unsupported time unit: " + unit);
            };
        }

        public static Duration convertToDuration(long value, TimeUnitType unit) {
            return switch (unit) {
                case NANOSECONDS -> Duration.ofNanos(value);
                case MICROSECONDS -> Duration.ofNanos(value * 1000);
                case MILLISECONDS -> Duration.ofMillis(value);
                case SECONDS -> Duration.ofSeconds(value);
                case MINUTES -> Duration.ofMinutes(value);
                case HOURS -> Duration.ofHours(value);
                case DAYS -> Duration.ofDays(value);
                default -> throw new IllegalArgumentException("Unsupported time unit: " + unit);
            };
        }
    }

}

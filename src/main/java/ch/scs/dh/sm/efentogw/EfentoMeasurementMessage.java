package ch.scs.dh.sm.efentogw;

import java.util.List;

public class EfentoMeasurementMessage {

    public List<Measurement> measurements;

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public static class Measurement {

        public String serial;
        public Integer response_handle;
        public String battery;
        public Integer signal;
        public String measured_at;
        public Integer measurement_interval;
        public String next_measurement_at;
        public List<Parameters> params;

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public Integer getResponse_handle() {
            return response_handle;
        }

        public void setResponse_handle(Integer response_handle) {
            this.response_handle = response_handle;
        }

        public String getBattery() {
            return battery;
        }

        public void setBattery(String battery) {
            this.battery = battery;
        }

        public Integer getSignal() {
            return signal;
        }

        public void setSignal(Integer signal) {
            this.signal = signal;
        }

        public String getMeasured_at() {
            return measured_at;
        }

        public void setMeasured_at(String measured_at) {
            this.measured_at = measured_at;
        }

        public Integer getMeasurement_interval() {
            return measurement_interval;
        }

        public void setMeasurement_interval(Integer measurement_interval) {
            this.measurement_interval = measurement_interval;
        }

        public String getNext_measurement_at() {
            return next_measurement_at;
        }

        public void setNext_measurement_at(String next_measurement_at) {
            this.next_measurement_at = next_measurement_at;
        }

        public List<Parameters> getParams() {
            return params;
        }

        public void setParams(List<Parameters> params) {
            this.params = params;
        }

        public static class Parameters {
            public Integer channel;
            public String type;
            public Double value;

            public Integer getChannel() {
                return channel;
            }

            public void setChannel(Integer channel) {
                this.channel = channel;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Double getValue() {
                return value;
            }

            public void setValue(Double value) {
                this.value = value;
            }
        }
    }
}


package no.ntnu.idata2900.project.esg_module_backend.sources;

public interface DataSource {
    void start();

    void stop();

    void setDataListener(DataListener listener);
}

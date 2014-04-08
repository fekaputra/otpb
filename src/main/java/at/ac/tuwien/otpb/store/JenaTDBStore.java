package at.ac.tuwien.otpb.store;

import at.ac.tuwien.genben.xml.TestCase;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;

import com.hp.hpl.jena.tdb.TDBFactory;
import org.apache.jena.riot.RDFFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class JenaTDBStore implements Store {
    Dataset dataset;
    @Override
    public void initStore(TestCase testCase) {
        dataset = TDBFactory.createDataset();
    }

    @Override
    public void closeStore() {
       dataset.close();
    }

    @Override
    public void deleteStore() {

    }

    @Override
    public String[] getFileOrDirectoryList() {
        return new String[0];
    }

    @Override
    public void loadRdf(String filename, RDFFormat format) {
       Model model = dataset.getDefaultModel();
        try {
            FileInputStream input = new FileInputStream(new File(filename));
            model.read(input, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Model getRepositoryConnection() {
        return dataset.getDefaultModel();
    }

    @Override
    public Model getQueryConnection() {
        return dataset.getDefaultModel();
    }
}
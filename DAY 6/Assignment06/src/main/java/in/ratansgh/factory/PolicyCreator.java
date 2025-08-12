package in.ratansgh.factory;

import in.ratansgh.model.Policy;

public interface PolicyCreator {
    Policy create(String policyId, double premium, int termYears);
}

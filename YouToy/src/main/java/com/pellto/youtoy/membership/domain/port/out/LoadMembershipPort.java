package com.pellto.youtoy.membership.domain.port.out;

import com.pellto.youtoy.membership.domain.model.Membership;

public interface LoadMembershipPort {

  Membership load(Long id);
}

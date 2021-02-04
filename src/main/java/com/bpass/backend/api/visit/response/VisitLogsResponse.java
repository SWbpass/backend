package com.bpass.backend.api.visit.response;

import com.bpass.backend.api.visit.model.Visits;
import com.bpass.backend.api.visit.model.dto.VisitsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitLogsResponse {
  private List<VisitsDto> visitLogs;
}

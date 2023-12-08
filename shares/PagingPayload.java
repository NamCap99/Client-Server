package shares;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * paging payload in pagination
 * @param <T>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagingPayload<T> {
    private T data;
    private Long total;
    private Integer page;
    private Integer limit;

    public PagingPayload(T data, Long total, Integer page, Integer limit) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.limit = limit;
    }

    public PagingPayload() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public static PagingPayload empty() {
        return new PagingPayload();
    }
}

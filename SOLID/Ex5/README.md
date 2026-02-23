# Ex5 — LSP: File Exporter Hierarchy

## 1. Context
A reporting tool exports student performance data to multiple formats.

## 2. Current behavior
- `Exporter` has `export(ExportRequest)` that returns `ExportResult`
- `PdfExporter` throws for large content (tightens preconditions)
- `CsvExporter` silently changes meaning by dropping newlines and commas poorly
- `JsonExporter` returns empty on null (inconsistent contract)
- `Main` demonstrates current behavior

## 3. What’s wrong (at least 5 issues)
1. Subclasses violate expectations of the base `Exporter` contract.
2. `PdfExporter` throws for valid requests (from base perspective).
3. `CsvExporter` changes semantics of fields (data corruption risk).
4. `JsonExporter` handles null differently than others.
5. Callers cannot rely on substitutability; they need format-specific workarounds.
6. Contract is not documented; behavior surprises are runtime.

## 4. Your task
Checkpoint A: Run and capture output.
Checkpoint B: Define a clear base contract (preconditions/postconditions).
Checkpoint C: Refactor hierarchy so all exporters honor the same contract.
Checkpoint D: Keep observable outputs identical for current inputs.

## 5. Constraints
- Keep `Main` outputs unchanged for the given samples.
- No external libraries.
- Default package.

## 6. Acceptance criteria
- Base contract is explicit and enforced consistently.
- No exporter tightens preconditions compared to base contract.
- Caller should not need `instanceof` to be safe.

## 7. How to run
```bash
cd SOLID/Ex5/src
javac *.java
java Main
```

## 8. Sample output
```text
=== Export Demo ===
PDF: ERROR: PDF cannot handle content > 20 chars
CSV: OK bytes=42
JSON: OK bytes=61
```

## 9. Hints (OOP-only)
- If a subtype cannot support the base contract, reconsider inheritance.
- Prefer composition: separate “format encoding” from “delivery constraints”.

## 10. Stretch goals
- Add a new exporter without changing existing exporters.

## 11. Refactoring Resolution (LSP)
The Exporter hierarchy violated the Liskov Substitution Principle by subclasses altering the base `Exporter` contract in unpredictably silent ways (throwing new exceptions, mishandling nulls, mangling data semantics). We fixed this via Composition over Inheritance:
- Created the **`FormatEncoder`** interface for pure parsing logic, and **`ExportConstraint`** interface for validation logic.
- Transformed the base **`Exporter`** into a strictly enforced contract that verifies null rules globally, validates injected `ExportConstraint` constraints, and strictly encodes via injected `FormatEncoder`.
- Subclasses (`PdfExporter`, `JsonExporter`, `CsvExporter`) now define themselves explicitly by passing their components up to `super()`.
- Data semantics in `CsvEncoder` were corrected to use standard CSV escaping (`"`) instead of silently replacing commas and newlines with spaces, resolving the semantic drift.
- Clients can confidently use `Exporter` knowing preconditions are enforced universally and postconditions match perfectly without runtime surprises.
